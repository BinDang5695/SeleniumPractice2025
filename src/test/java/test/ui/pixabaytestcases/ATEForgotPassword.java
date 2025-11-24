package test.ui.pixabaytestcases;

import com.mailslurp.models.InboxDto;
import org.testng.Assert;
import org.testng.annotations.Test;
import settings.helpers.EmailHelper;
import settings.helpers.PropertiesHelper;
import settings.utils.LogUtils;
import test.ui.common.BaseTest;

import java.util.UUID;

public class ATEForgotPassword extends BaseTest {

    @Test
    public void testForgotPassword() throws Exception {
        // khởi tạo MailSlurp
        String apiKey = PropertiesHelper.getValue("API_KEY");
        EmailHelper emailHelper = new EmailHelper(apiKey);
        InboxDto inbox = emailHelper.createTestInbox();
        UUID inboxId = inbox.getId();
        String inboxEmail = inbox.getEmailAddress();
        LogUtils.info("Inbox created: " + inboxEmail);

        // trigger forgot password với email test
        loginPixabay().forgotPassword(inboxEmail);

        // verify email
        boolean emailReceived = emailHelper.verifyForgotPasswordEmail(
                inboxId,
                "Reset your Pexels password",
                "Click the link below to reset your password",
                30
        );

        LogUtils.info("Email verification: " + emailReceived);
        Assert.assertTrue(emailReceived, "❌ Forgot password email not received or content mismatch");
    }
}

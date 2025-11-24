package settings.helpers;

import com.mailslurp.apis.EmailControllerApi;
import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.models.Email;
import com.mailslurp.models.EmailPreview;
import com.mailslurp.models.InboxDto;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EmailHelper {

    private final InboxControllerApi inboxApi;
    private final EmailControllerApi emailApi;

    public EmailHelper(String apiKey) {
        ApiClient client = new ApiClient();
        client.setApiKey(apiKey);

        // tăng timeout HTTP client để tránh SocketTimeoutException
        client.setHttpClient(
                client.getHttpClient().newBuilder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .build()
        );

        inboxApi = new InboxControllerApi(client);
        emailApi = new EmailControllerApi(client);
    }

    /** Tạo inbox test */
    public InboxDto createTestInbox() throws Exception {
        return inboxApi.createInboxWithDefaults();
    }

    /** Xóa inbox test (tuỳ chọn cleanup) */
    public void deleteInbox(UUID inboxId) throws Exception {
        inboxApi.deleteInbox(inboxId);
    }

    /** Chờ email tới inbox, lấy Email đầy đủ */
    public Email waitForLatestFullEmail(UUID inboxId, long timeoutMillis, boolean unreadOnly) throws Exception {
        long waited = 0;
        long interval = 5000; // poll mỗi 5 giây
        Long unreadFlag = unreadOnly ? 1L : 0L;

        while (waited < timeoutMillis) {
            List<EmailPreview> previews = inboxApi.getEmails(
                    inboxId, null, null, null, null, null,
                    unreadFlag, null, null, null
            );

            if (previews != null && !previews.isEmpty()) {
                UUID emailId = previews.get(0).getId();
                Email fullEmail = emailApi.getEmail(emailId, true);
                return fullEmail;
            }

            Thread.sleep(interval);
            waited += interval;
        }

        return null;
    }

    /** Verify email Forgot Password (subject + content) */
    public boolean verifyForgotPasswordEmail(UUID inboxId, String expectedSubject, String expectedContent, int timeoutSeconds) throws Exception {
        Email latest = waitForLatestFullEmail(inboxId, timeoutSeconds * 1000L, true);
        if (latest == null) return false;

        // subject + body contains expected text
        return latest.getSubject().contains(expectedSubject) && latest.getBody().contains(expectedContent);
    }
}

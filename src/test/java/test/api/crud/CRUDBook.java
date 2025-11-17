package test.api.crud;

import settings.globals.EndPointGlobal;
import settings.helpers.JsonHelper;
import settings.keywords.ApiKeyword;
import test.api.common.BaseTest;
import test.api.common.VerifyDataBookBody;
import test.api.listeners.TestListener;
import test.api.model.BookPOJO_Lombok;
import test.api.model.data.BookPOJO_Lombok_Builder;
import settings.utils.LogUtils;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

@Listeners(TestListener.class)
public class CRUDBook extends BaseTest {

    private BookPOJO_Lombok book;
    String dataFile = "src/test/resources/testdata/CreateBook.json";

    @Severity(SeverityLevel.CRITICAL)
    @Epic("Regression Test")
    @Feature("Book Test")
    @Story("Test Add New Book")
    @Description("Add new Book")
    @Link("https://jira.com/anhtester/apitest/user/10")
    @Test(priority = 1)
    public void testAddNewBook() {

        book = BookPOJO_Lombok_Builder.getDataToCreateBook();

        JsonHelper.updateValueJsonFile(dataFile, book);

        Response response = ApiKeyword.post(EndPointGlobal.EP_BOOK, new File(dataFile));

        JsonHelper.updateValueJsonFile(dataFile, book);

        VerifyDataBookBody.verifyResponseSuccess(response, book, 200);

    }

    @Test(priority = 2)
    public void getBook(){
        LogUtils.info("ID: " + book.getId());

        Response response = ApiKeyword.get(EndPointGlobal.EP_BOOK + "/" + book.getId());

        VerifyDataBookBody.verifyResponseSuccess(response, book, 200);

    }

    @Test(priority = 3)
    public void putBook() throws Exception {

        book = BookPOJO_Lombok_Builder.createUpdatedBook(book);

        JsonHelper.updateValueJsonFile(dataFile, book);

        String jsonBody = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(dataFile)));

        Response response = ApiKeyword.put(EndPointGlobal.EP_BOOK + "/" + book.getId(), jsonBody);

        VerifyDataBookBody.verifyResponseSuccess(response, book, 200);

    }

    @Test(priority = 4)
    public void getBookAfterPut(){
        LogUtils.info("ID: " + book.getId());

        Response response = ApiKeyword.get(EndPointGlobal.EP_BOOK + "/" + book.getId());

        VerifyDataBookBody.verifyResponseSuccess(response, book, 200);

    }

    @Test(priority = 5)
    public void deleteBook(){

        Response response = ApiKeyword.delete(EndPointGlobal.EP_BOOK + "/" + book.getId());

        VerifyDataBookBody.verifyResponseSuccessAfterDelete(response, book, 200);

    }

    @Test(priority = 6)
    public void getAfterDeleteBook() {

        Response response = ApiKeyword.get(EndPointGlobal.EP_BOOK + "/" + book.getId());

        JsonPath jsonPath = response.jsonPath();

        ApiKeyword.verifyStatusCode(response, 400);

        Assert.assertEquals(jsonPath.getString("message"), "Not found", "Message not match.");
        Assert.assertEquals(jsonPath.getString( "errors"), "No book found with the submitted id", "Error detail not match.");
        LogUtils.info("message: " + jsonPath.getString("message"));
        LogUtils.info("errors: " + jsonPath.getString("errors"));


    }


}
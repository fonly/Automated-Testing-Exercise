package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import pages.BaiduImagePage;
import testUtils.RetryAnalyzer;

public class BaiduImageSearchTests extends TestBase {

    @BeforeClass
    public void setupBeforeClass() {
        extentTest = reporter.createTest("baidu image serch", "Verify specified result");
    }


    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Parameters({"visit-result"})
    public void baiduImagesearchTest(Integer visitResult) {
        testNode = extentTest.createNode("search image");
        testNode.assignCategory("Search Image");
        baiduImagePage = new BaiduImagePage(page,testNode);
        baiduImagePage.searchImage("dog.png");

        String result = baiduImagePage.selectResult(visitResult);
        System.out.println(result);
        softAssert.assertTrue(baiduImagePage.checkoutResultRelated(result,"狗"),"if contianed keywords");

    }

}

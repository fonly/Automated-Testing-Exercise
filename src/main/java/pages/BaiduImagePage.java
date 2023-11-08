package pages;

import static base.PlaywrightFactory.takeScreenshot;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;

/**
 * Page Object Class for Baidu Home Page
 */
public class BaiduImagePage {

    private Page page;
    private ExtentTest extentTest;
    private final String searchImageIcon = "#form > span.bg.s_ipt_wr.new-pmd.quickdelete-wrap > span.soutu-btn";
    private final String uploadImage = "#form > div > div.soutu-state-normal > div.upload-wrap > input";
    private final String searchResultList = "#app > div > div:nth-child(3) > div > div.graph-row.graph-same-list";
    private final String resuleIndexSuffix ="> div:nth-child(%s)";
    private final double TIMEOUT = 1000000;


    public BaiduImagePage(Page page, ExtentTest extentTest) {
        this.page = page;
        this.extentTest = extentTest;
    }

    /**
     * search by image name.
     * image file locate user.dir
     * @param imageName dog.png
     * @return
     */
    public String searchImage(String imageName) {

        page.click(searchImageIcon);
        page.setInputFiles(uploadImage,Paths.get(""+imageName));
        Page.WaitForSelectorOptions waitForResuleVisible = new Page.WaitForSelectorOptions();
        waitForResuleVisible.setTimeout(TIMEOUT);
        waitForResuleVisible.setState(WaitForSelectorState.VISIBLE);
        page.waitForSelector(searchResultList, waitForResuleVisible);

        String screenShot = takeScreenshot(page);
        return screenShot;
    }

    /**
     * get the result of search
     * @param index  the index of results
     * @return
     */
    public String selectResult(int index){
        page.mouse().wheel(0,300);

        String result = page.locator(searchResultList + String.format(resuleIndexSuffix,index)).first().textContent();

        return result;
    }

    public boolean checkoutResultRelated(String result,String keywords) {

        if (result.indexOf(keywords)>0) {
            return true;
        } else {
            return false;
        }
    }
}

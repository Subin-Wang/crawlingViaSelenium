package net.selenium.crawling;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeleniumCrawlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumCrawlingApplication.class, args).getBean(SeleniumCrawlingApplication.class)
				.test();
	}

	private void test() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		int pageNum = 1;
		String[] testList= {};

		WebDriver detailDriver = new ChromeDriver(options);
		try {
			/*****************************           로그인         ********************************/
			detailDriver.get("https://www.freemoa.net/m5/s50");

			detailDriver.findElement(By.xpath("//*[@id=\"loginIdInput\"]")).sendKeys("ksubin915");
			detailDriver.findElement(By.xpath("//*[@id=\"loginPwInput\"]")).sendKeys("pag623623*");

			detailDriver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).sendKeys(Keys.ENTER);
			System.out.println("로그인 성공 detailDriver : " + detailDriver.getCurrentUrl());
			/*****************************           로그인         ********************************/
			
			Loop1:while (pageNum <= 10) {
				/*****************************         페이지이동    ********************************/
				System.out.println("현재 페이지 : " + pageNum);
				String url = "https://www.freemoa.net/m4/s41?page=" + pageNum;
				detailDriver.navigate().to(url);
				/*****************************         페이지이동    ********************************/
				
				/*****************************         제목 링크    ********************************/
				//detailDriver.findElement(By.xpath("//*[@id=\"projectListNew\"]/li[1]/div[1]/div[1]/p")).sendKeys(Keys.ENTER);
				
				testList = detailDriver.getPageSource().split("data-pno=");
				System.out.println("testList : " + testList.toString());
				
				/*****************************         제목 링크    ********************************/	
				//태그 // 등록일 // 제목 // 비용 // 기간 // 지원자수 // 마감일정 // 관련기술 // 요약 //업무내용
			
				
				
				/* 엑셀 연결
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("데이터");
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(pageNum);
				
				FileOutputStream stream=null;
				try {
				stream = new FileOutputStream("C:\\Users\\admin\\testData.xlsx");
				workbook.write(stream);
				System.out.println("파일생성완료");
				}catch(IOException e) {
					System.out.println("파일생성실패");
					e.getStackTrace();
				}finally {
					stream.close();
				}*/
				
				pageNum++;
			}
			
			for(String thisNo : testList) {
				System.out.println(thisNo);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (detailDriver != null) {
				detailDriver.quit();
			}
		}
	}
}

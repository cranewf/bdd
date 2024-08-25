package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;

public class TransferPage {
    private SelenideElement amountReplenishment = $("[data-test-id='amount'] input");
    private SelenideElement fromWhichCard = $("[data-test-id='from'] input");
    private SelenideElement toWhichCard = $("[data-test-id='to'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement transferHead = $("h1");
    private SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public TransferPage() {
        transferHead.shouldBe(Condition.visible);
    }

    public void transfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountReplenishment.setValue(amountToTransfer);
        fromWhichCard.setValue(cardInfo.getNumberCard());
        transferButton.click();
    }

    public DashboardPage validTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        transfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void findErrorMassage(String expectedText) {
        errorMessage.shouldHave(Condition.text(expectedText))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

}

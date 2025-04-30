package com.Amex.core.pages;

import com.Amex.core.model.Element;

import static com.Amex.core.ui.UICommon.page;

public class Cartes_pour_les_particuliers
{
    public static void selectCartes()
    {
        Element cartes_pour_les_particuliers = new Element(page, "//h2[contains(text(), 'Devenir client')]//parent::div//a[contains(text(), 'Cartes pour les particuliers')]");
        cartes_pour_les_particuliers.waitForElementToBeVisible();
        cartes_pour_les_particuliers.clickElement();
    }

    public static void selectGold()
    {
        Element gold = new Element(page, "//div[@alt = \"Carte Gold American Express\"]//parent::a//parent::div//parent::div//following-sibling::div[@class = \"button parbase\"]//a");
        gold.waitForElementToBeVisible();
        gold.clickElement();
    }

    public static void selectDemandez()
    {
        Element demandez = new Element(page, "//div[@class=\"stickySideRail-modules__desktopExtraSpace___10ITy sc_paddingTop_30\"]//div[@data-qe-id = \"CallToActionsWithLinks\"]//a[contains(text(), 'Demandez votre Carte')]");
        demandez.waitForElementToBeVisible();
        demandez.clickElement();
    }

    public static void fillform()
    {
        Element gender = new Element(page, "//input[@type = \"radio\" and @id = \"MR\"]//parent::div/label");
        gender.waitForElementToBeVisible();
        gender.clickElement();
        Element firstName = new Element(page, "//input[@id = \"fieldControl-input-firstName\"]");
        firstName.waitForElementToBeVisible();
        firstName.fillText("John");
        Element lastName = new Element(page, "//input[@id = \"fieldControl-input-lastName\"]");
        lastName.waitForElementToBeVisible();
        lastName.fillText("Doe");
        Element dateOfBirth = new Element(page, "//input[@id = \"fieldControl-input-dateOfBirth\"]");
        dateOfBirth.waitForElementToBeVisible();
        dateOfBirth.fillText("01/01/1990");
        Element email = new Element(page, "//input[@id = \"fieldControl-input-email\"]");
        email.waitForElementToBeVisible();
        email.fillText("test@test.com");
        Element phone = new Element(page, "//input[@id = \"fieldControl-input-mobilePhoneNumber\"]");
        phone.waitForElementToBeVisible();
        phone.fillText("0673774878");
        Element submit = new Element(page, "//button[contains(text(), 'Sauvegarder et Continuer')]");
        submit.waitForElementToBeVisible();
        submit.clickElement();
    }
}

package com.yourcompany.pages;

/**
 * Interface Segregation Principle (ISP): Split interfaces so that classes only implement what they need.
 * If there are more actions on a page, create smaller interfaces
 * implement this interface in pages that support logout
 */
public interface LogoutPage {
    void logout();
}

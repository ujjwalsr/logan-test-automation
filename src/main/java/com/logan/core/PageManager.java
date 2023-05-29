package com.logan.core;

public class PageManager {

    private static ThreadLocal<PageManager> INSTANCE = ThreadLocal.withInitial(PageManager::new);
    private PageGenerator pageGenerator;

    public static synchronized PageManager getInstance() {
        return INSTANCE.get();
    }

    public static synchronized void cleanUp() {
        INSTANCE.remove();
    }

    public void closeDriver() {
        this.pageGenerator.driver.quit();
    }

    public void initialisePageGenerator() {
        this.pageGenerator = new PageGenerator(new DriverSupplier().initializeDriver());
    }

    public PageGenerator getPageGenerator() {
        return this.pageGenerator;
    }

}
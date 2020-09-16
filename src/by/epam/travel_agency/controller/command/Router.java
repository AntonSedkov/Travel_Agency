package by.epam.travel_agency.controller.command;

import by.epam.travel_agency.util.PathManager;

public class Router {
    enum DispatchType {
        FORWARD, REDIRECT
    }

    private String page = PathManager.getProperty(PathManager.KEY_PAGE_INDEX);
    private DispatchType dispatchType = DispatchType.FORWARD;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public void setRedirect() {
        this.dispatchType = DispatchType.REDIRECT;
    }

}
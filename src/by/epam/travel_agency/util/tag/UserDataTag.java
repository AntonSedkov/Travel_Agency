package by.epam.travel_agency.util.tag;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.model.entity.ClientSheet;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class UserDataTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        ClientSheet defaultSheet = new ClientSheet();
        ClientSheet sheet = (ClientSheet) session.getAttribute(AttributeName.SHEET);
        sheet = (sheet != null) ? sheet : defaultSheet;
        String defaultUsername = "Traveler";
        String username = (String) session.getAttribute(AttributeName.USER);
        username = (username != null) ? username : defaultUsername;
        String email = (String) session.getAttribute(AttributeName.EMAIL);
        email = (email != null) ? email : "";
        String defaultRole = "Guest";
        String role = (String) session.getAttribute(AttributeName.ROLE);
        role = (role != null) ? role : defaultRole;
        String top = "<li class=\"nav-item nav-text m-2\">";
        String endLiStartLi = "</li><li class=\"text-center m-2\">";
        StringBuilder buildData = new StringBuilder().append(sheet.getSheetSum()).append("&yen;").append(endLiStartLi).append(username).append(endLiStartLi)
                .append(email).append(endLiStartLi).append(role).append("</li>");
        try {
            JspWriter out = pageContext.getOut();
            out.write(top + buildData.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
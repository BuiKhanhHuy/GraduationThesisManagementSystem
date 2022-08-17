package com.buikhanhhuy.controllers.admin;

import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.service.CouncilDetailService;
import com.buikhanhhuy.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller(value = "AdminCouncilDetailController")
@RequestMapping(path = "/admin")
public class CouncilDetailController {
    @Autowired
    private SchoolYearService schoolYearService;
    @Autowired
    private CouncilDetailService councilDetailService;

    @GetMapping(path = "/councils-detail")
    public String councilDetailList(Model model, HttpSession session, @RequestParam(required = false) Map<String, String> params) {
        User user = (User) session.getAttribute("currentUser");

        if (user != null && user.getLecturer() != null) {
            int lecturerId = user.getLecturer().getId();

            model.addAttribute("schoolYearOptions", this.schoolYearService.getSchoolYearOptions());

            model.addAttribute("page", Integer.parseInt((params.get("page") != null && !params.get("page").isEmpty()) ? params.get("page") : "1"));
            model.addAttribute("totalPage", this.councilDetailService.countCouncilDetail(lecturerId, params));
            model.addAttribute("councilsDetail", this.councilDetailService.getCouncilsDetail(lecturerId, params));
        }

        return "adminCouncilDetailList";
    }

    @GetMapping(path = "/councils-detail/{councilDetailId}")
    public String councilDetailDetail(Model model, @PathVariable(value = "councilDetailId") int councilDetailId) {

        model.addAttribute("councilDetailDetail", this.councilDetailService.getCouncilDetailById(councilDetailId));

        return "adminCouncilDetailDetail";
    }
}

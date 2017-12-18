package loan.mvc;

import loan.model.MonthSchedule;
import loan.model.PayInfo;
import loan.model.Request;
import loan.service.RequestService;
import loan.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author A.Bogoslov
 */

@Controller
@SessionAttributes({"request"})
public class MainController {

    private final RequestService requestService;
    private final Utils utils;

    @Autowired
    public MainController(RequestService service, Utils utils) {
        this.requestService = service;
        this.utils = utils;
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/requests")
    public String listRequests(Model model) {
        model.addAttribute("requests", requestService.listRequests());
        return "request-listing";
    }

    @RequestMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("request", new Request());
        return "input-edition";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String createRequest(@ModelAttribute("request") Request request) {

        request.setMonthlyCharge(utils.calcMonthlyCharge(request));
        requestService.createRequest(request);
        return "success";
    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public String updateRequest(@ModelAttribute("request") Request request) {

        request.setMonthlyCharge(utils.calcMonthlyCharge(request));
        requestService.updateRequest(request);
        return "success";
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET)
    public String getRequestInfo(@PathVariable("id") int id,
                                 Model model) {

        model.addAttribute(requestService.getRequest(id));
        return "input-edition";
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.DELETE)
    public String deleteRequest(@PathVariable("id") int id) {

        requestService.removeRequest(id);
        return "request-listing";
    }

    @RequestMapping("/schedule/{id}")
    public String schedulePayment(@PathVariable("id") int id,
                                  Model model) {

        Request request = requestService.getRequest(id);
        BigDecimal monthlyCharge = utils.calcMonthlyCharge(request);
        List<MonthSchedule> schedule = utils.calcSchedule(request.getDuration(), request.getSum(), monthlyCharge);
        PayInfo info = utils.calcInfo(schedule, request.getSum(), request.getDuration());


        model.addAttribute("date", request.getDate().toLocalDate());
        model.addAttribute("info", info);
        model.addAttribute("schedule", schedule);
        model.addAttribute("charge", monthlyCharge);
        return "payment-schedule";
    }
}

package loan.mvc;

import loan.model.Client;
import loan.model.MonthSchedule;
import loan.model.PayInfo;
import loan.model.Request;
import loan.service.ClientService;
import loan.service.RequestService;
import loan.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author A.Bogoslov
 */

@Controller
@SessionAttributes({"request"})
public class MainController {

    private final RequestService requestService;
    private final ClientService clientService;
    private final Utils utils;

    private static Logger logger = Logger.getLogger(MainController.class.getName());

    @Autowired
    public MainController(RequestService service, ClientService clientService, Utils utils) {
        this.requestService = service;
        this.clientService = clientService;
        this.utils = utils;
        logger.info("Autowiring successfully");
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
        logger.info("Put List<Request> attribute to model");
        return "request-listing";
    }

    @RequestMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("request", new Request());
        logger.info("Put Request attribute to model");

        model.addAttribute("client", new Client());
        logger.info("Put Client attribute to model");

        return "input-edition";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String createRequest(@ModelAttribute("request") Request request) {
        request.setMonthlyCharge(utils.calcMonthlyCharge(request));

        Client formClient = request.getClient();
        Client dbClient = clientService.findClient(formClient);
        if (dbClient == null) {
            clientService.createClient(formClient);
            logger.info("Client inserted to db");
        } else {
            request.setClient(dbClient);
            logger.info("Existing Client selected from db");
        }

        requestService.createRequest(request);
        logger.info("Request inserted to db");
        return "success";
    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public String updateRequest(@ModelAttribute("request") Request request) {
        request.setMonthlyCharge(utils.calcMonthlyCharge(request));
        requestService.updateRequest(request);
        logger.info("Request updated in db");
        return "success";
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET)
    public String getRequestInfo(@PathVariable("id") int id,
                                 Model model) {

        model.addAttribute("request", requestService.getRequest(id));
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
        logger.info("Put Date attribute to model");

        model.addAttribute("info", info);
        logger.info("Put PayInfo attribute to model");

        model.addAttribute("schedule", schedule);
        logger.info("Put List<MonthSchedule> attribute to model");

        model.addAttribute("charge", monthlyCharge);
        logger.info("Put BigDecimal attribute to model");

        model.addAttribute("client", request.getClient());
        logger.info("Put Client attribute to model");
        return "payment-schedule";
    }
}

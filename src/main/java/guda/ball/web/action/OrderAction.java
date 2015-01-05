package guda.ball.web.action;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import guda.ball.biz.OrderBiz;
import guda.ball.dao.domain.OrderDO;
import guda.ball.web.form.OrderEditForm;
import guda.ball.web.form.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class OrderAction {


    @Autowired
    private OrderBiz orderBiz;

    @RequestMapping(value = "order/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = orderBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "order/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "order/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, OrderEditForm orderEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = orderBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            orderEditForm.initForm(((OrderDO)bizResult.data.get("orderDO")));
            return "order/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "order/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = orderBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "order/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "order/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, OrderForm orderForm,
        BindingResult result, Map<String,Object> model) {
        return "order/create.vm";
    }

    @RequestMapping(value = "order/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  OrderForm orderForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "order/create.vm";
        }
        OrderDO orderDO = orderForm.toDO();
        BizResult bizResult = orderBiz.create(orderDO);
        if (bizResult.success) {
            return "redirect:/order/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "order/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid OrderEditForm orderEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "order/edit.vm";
        }
        OrderDO orderDO = orderEditForm.toDO();
        BizResult bizResult = orderBiz.update(orderDO);
        if (bizResult.success) {
            return "redirect:/order/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "order/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = orderBiz.delete(id);
        if (bizResult.success) {
            return "order/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}

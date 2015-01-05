package guda.ball.web.action;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import guda.ball.biz.GoodsBiz;
import guda.ball.dao.domain.GoodsDO;
import guda.ball.web.form.GoodsEditForm;
import guda.ball.web.form.GoodsForm;
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
public class GoodsAction {


    @Autowired
    private GoodsBiz goodsBiz;

    @RequestMapping(value = "goods/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = goodsBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "goods/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "goods/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, GoodsEditForm goodsEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = goodsBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            goodsEditForm.initForm(((GoodsDO)bizResult.data.get("goodsDO")));
            return "goods/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "goods/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = goodsBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "goods/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "goods/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, GoodsForm goodsForm,
        BindingResult result, Map<String,Object> model) {
        return "goods/create.vm";
    }

    @RequestMapping(value = "goods/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid GoodsForm goodsForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "goods/create.vm";
        }
        GoodsDO goodsDO = goodsForm.toDO();
        BizResult bizResult = goodsBiz.create(goodsDO);
        if (bizResult.success) {
            return "redirect:/goods/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "goods/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid GoodsEditForm goodsEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "goods/edit.vm";
        }
        GoodsDO goodsDO = goodsEditForm.toDO();
        BizResult bizResult = goodsBiz.update(goodsDO);
        if (bizResult.success) {
            return "redirect:/goods/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "goods/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = goodsBiz.delete(id);
        if (bizResult.success) {
            return "redirect:/goods/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}

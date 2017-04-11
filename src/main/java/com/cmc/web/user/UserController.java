package com.cmc.web.user;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmc.common.BizException;
import com.cmc.common.constants.AjaxGeneralResult;
import com.cmc.common.utils.FileUploadUtil;
import com.cmc.permission.facade.model.PermissionModel;
import com.cmc.permission.facade.service.PermissionService;
import com.cmc.user.facade.entity.User;
import com.cmc.user.facade.model.UserModel;
import com.cmc.user.facade.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("user.htm")
@Api(value = "user.htm", description = "用户")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class);

    /** 分页大小 */
    private static final long PAGE_SIZE = 10L;

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    // @Autowired
    // private CacheManager cacheManager;
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(method = RequestMethod.GET, params = "action=add")
    public String add() {
        try {
            /** EhCache DEMO */
            CacheManager cacheManager = CacheManager.create();
            Cache cache = cacheManager.getCache("sample");
            Element element = new Element("name", "Thomas");
            cache.put(element);
            cache.get("name");
        } catch (Exception e) {
            LOG.error("", e);
        }
        return "user/detail";
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "action=add")
    public AjaxGeneralResult add(HttpServletRequest request, UserModel dto) {
        try {
            String url = FileUploadUtil.upload(request);
            if (StringUtils.isBlank(url)) {
                return AjaxGeneralResult.getFailureRst("上传图片");
            }
            dto.setTemp2(url);
            return userService.add(dto) ? AjaxGeneralResult.getSuccessRst("添加用户信息") : AjaxGeneralResult.getFailureRst("添加用户信息");
        } catch (IllegalArgumentException e) {
            LOG.error("", e);
            return AjaxGeneralResult.getFailureRst("添加用户信息");
        } catch (BizException e) {
            LOG.error("", e);
            return AjaxGeneralResult.getFailureRst("添加用户信息");
        } catch (Exception e) {
            LOG.error("", e);
            return AjaxGeneralResult.getFailureRst("添加用户信息");
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = "action=list")
    public String list(HttpServletRequest request, ModelMap map) throws Exception {

        String pageNo = request.getParameter("pageNo");
        if (null == pageNo) {
            pageNo = "1";
        }
        map.put("users", userService.list(Long.parseLong(pageNo), PAGE_SIZE));
        return "user/list";
    }

    @RequestMapping(method = RequestMethod.GET, params = "action=get")
    public String get(UserModel dto, ModelMap map) {
        map.put("user", userService.get(dto));
        return "user/detail";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "action=update")
    public AjaxGeneralResult update(UserModel dto) {
        return userService.update(dto) ? AjaxGeneralResult.getSuccessRst("更新用户信息") : AjaxGeneralResult.getFailureRst("更新用户信息");
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "action=delete")
    public AjaxGeneralResult delete(UserModel dto) {
        return userService.delete(dto) ? AjaxGeneralResult.getSuccessRst("删除用户信息") : AjaxGeneralResult.getFailureRst("删除用户信息");
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = "action=getsampleuser")
    public User getSampleUser() {
        User user = new User();
        user.setId("111111");
        return user;
    }

    /**
     * 数据校验：后端（通过AJAX）间接校验
     * 
     * @author Thomas Lee
     * @since 2016年12月22日 上午10:16:56
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "action=existstheuser")
    public AjaxGeneralResult existsTheUser(String userId) {
        /* business logic is omitted. */
        return new Random().nextBoolean() ? AjaxGeneralResult.getSuccessRst() : AjaxGeneralResult.getFailureRst();
    }

    /**
     * 获取所有权限列表
     * @author Thomas Lee
     * @version 2017年3月13日 下午5:55:31
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = "action=getpermissions")
    public AjaxGeneralResult getAllPermissions() {
        // List<PermissionModel> mPermissions = permissionService.getAll();
        PermissionModel mPermission = permissionService.get(1L);
        LOG.info(mPermission.getPermName());
        return AjaxGeneralResult.getSuccessRst();
    }

}
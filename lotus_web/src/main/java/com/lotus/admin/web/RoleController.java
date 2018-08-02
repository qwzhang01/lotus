package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Role;
import com.lotus.rpc.service.auth.PermissionRpcService;
import com.lotus.rpc.service.auth.RoleRpcService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRpcService roleService;
    @Autowired
    private PermissionRpcService permissionService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "role/list";
    }

    @RequiresPermissions(value = {"角色管理"}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Page page(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                     @RequestParam(value = "roleName", required = false) String roleName) {
        Page<Map<String, Object>> list = roleService.findPageList(roleName, pageNumber, pageSize);
        return list;
    }

    @RequiresPermissions(value = {"角色管理-新增", "角色管理-编辑"}, logical = Logical.OR)
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "roleId", defaultValue = "0", required = false) Integer roleId, Model model) {
        if (0 != roleId) {
            Role role = roleService.getById(roleId);
            model.addAttribute(role);
        }
        return "role/form";
    }

    @RequiresPermissions(value = {"角色管理-新增", "角色管理-编辑"}, logical = Logical.OR)
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Role role, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(role.getRolename())) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("角色名称不能为空"));
            return "redirect:/role/form";
        }
        Boolean result = false;
        if (role.getRoleid() != null && role.getRoleid() != 0) {
            result = roleService.update(role);
        } else {
            result = roleService.add(role);
        }
        if (result) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("保存成功"));
        } else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("保存失败"));
        }
        return "redirect:/role/list";
    }

    @RequiresPermissions(value = {"角色管理-删除"}, logical = Logical.OR)
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "roleId", required = false, defaultValue = "0") Integer roleId,
                         RedirectAttributes redirectAttributes) {
        if (roleId == 0) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("角色ID不能为空"));
            return "redirect:/role/list";
        }
        Role role = roleService.getById(roleId);
        if (role == null) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除的角色不存在"));
            return "redirect:/role/list";
        }
        boolean result = roleService.delete(role);
        if (result) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("删除成功"));
        } else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除失败"));
        }
        return "redirect:/role/list";
    }

    /**
     * 分配权限
     *
     * @return
     */
    @RequiresPermissions(value = {"角色管理-分配权限"}, logical = Logical.OR)
    @RequestMapping(value = "assginPer", method = RequestMethod.GET)
    public String assginPer(@RequestParam(value = "roleId", required = false, defaultValue = "0") Integer roleId, ModelMap model) {
        model.addAttribute("roleId", roleId);
        return "role/assginPer";
    }

    /**
     * 分配权限
     *
     * @return
     */
    @RequiresPermissions(value = {"角色管理-分配权限"}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "saveAssign", method = RequestMethod.POST)
    public AjaxResult saveAssign(@RequestParam(value = "roleId", required = false, defaultValue = "0") Integer roleId,
                                 @RequestParam(value = "permissionId") Integer[] permissionId) {
        if (roleId == 0) {
            return AjaxResult.error("角色ID不能为空");
        }
        Role role = roleService.getById(roleId);
        if (role == null) {
            return AjaxResult.error("角色不存在");
        }
        boolean result = roleService.assginPermission(role, permissionId);

        if (result) {
            return AjaxResult.success("分配成功");
        } else {
            return AjaxResult.error("分配失败");
        }
    }

    @RequestMapping(value = "per_page/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> perList(@PathVariable(value = "roleId") Integer roleId) {
        List<Map<String, Object>> list = permissionService.findByRoleId(roleId);
        return list;
    }
}
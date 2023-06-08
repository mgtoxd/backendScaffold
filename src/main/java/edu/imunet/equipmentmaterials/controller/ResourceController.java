package edu.imunet.equipmentmaterials.controller;

import io.swagger.v3.oas.annotations.Operation;;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequestMapping("/resource")
@Tag(name = "资源管理")
public class ResourceController {

    @PostMapping("/list")
    @Operation(summary ="接口方法描述")
    public String list() {
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        return "list";
    }

}

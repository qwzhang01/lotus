package com.louts.service;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.pojo.Folder;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.attachment.FolderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class FolderServiceTest {
    @Autowired
    private FolderService folderService;

    @Test
    public void testFindAll() {
        List<Folder> all = folderService.findAll();
        JsonKit.jsonPrint(all);
    }

    @Test
    public void testGetById() {
        Folder folder = folderService.getById(20);
        JsonKit.jsonPrint(folder);
    }
}

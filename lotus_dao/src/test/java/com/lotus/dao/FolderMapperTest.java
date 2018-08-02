package com.lotus.dao;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.FolderMapper;
import com.lotus.dao.pojo.Folder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class FolderMapperTest {
    @Autowired
    private FolderMapper folderMapper;

    @Test
    public void testSelectRootFolder() {
        Folder folder = folderMapper.selectRootFolder();
        JsonKit.jsonPrint(folder);
        System.out.println(folder.getFullPath() + folder.getFullPath() + "/");
    }

    @Test
    public void testSelectAll() {
        List<Folder> list = folderMapper.selectAll();
        JsonKit.jsonPrint(list);
    }
}

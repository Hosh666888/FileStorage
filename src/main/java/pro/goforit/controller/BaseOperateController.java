package pro.goforit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pro.goforit.aspect.anno.CheckIfBelong2Me;
import pro.goforit.domain.common.ContextHolder;
import pro.goforit.domain.common.R;
import pro.goforit.domain.vo.FileBaseVO;
import pro.goforit.util.FileUtil;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Double>J
 * @email: zjj20001031@foxmail.com
 * @editTime: 2022/12/2 14:13
 * @desc:
 **/
@RestController
@RequestMapping("/")
@Slf4j
public class BaseOperateController {


    @GetMapping(value = "cd")
    public R<List<FileBaseVO>> cd(@RequestParam("path")String path){
        log.info("pwd:{}",path);
        File file = new File(ContextHolder.getHomeDir(), path);
        Assert.notNull(file,"dir not exist.");
        if (file.isFile()){
            throw new IllegalArgumentException("The directory name is invalid.");
        }

        File[] files = file.listFiles();
        ArrayList<FileBaseVO> vos = new ArrayList<>(10);

        if (files!=null){
            for (File item : files) {
                FileBaseVO vo = new FileBaseVO();
                vo.setName(item.getName());
                vo.setLastModify(item.lastModified());
                vo.setAbsolutePath(item.getAbsolutePath());
                vo.setTotalSpace(item.getTotalSpace());

                if (item.isDirectory()){
                    vo.setIfDir(true);
                }else{
                    vo.setExtend(FileUtil.getFileExtend(item.getName()));
                }
                vos.add(vo);
            }
        }else{
            throw new IllegalArgumentException("directory not exist.");
        }

        return R.ok(vos);
    }

    @PostMapping("rename")
    @CheckIfBelong2Me(path = "#path")
    public R<String> rename(@RequestParam("path")String path,@RequestParam("newName")String newName){
        File file = new File(path);
        Assert.notNull(file,"file not exist.");
        String parent = file.getParent();
        log.info("parent:{}",parent);
        boolean b = file.renameTo(new File(parent, newName));
        return b?R.ok(null,"???????????????") : R.fail("???????????????");
    }


}

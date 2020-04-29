/*
 *     Copyright © Kashin Vladimir Olegovich (Wain Key), 2020.
 *     This project is a part of a graduation qualification work of Bauman Moscow State Technical University (BMSTU) student.
 *     If You use this project or a part of it for your own purposes please make sure that You indicated authorship.
 */

package org.wainkey.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wainkey.model.Data;
import org.wainkey.model.StationData;
import org.wainkey.service.StationService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class StationController {
    private static StationData stationData;
    private StationService stationService;
    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    private static Logger LOG = Logger.getLogger(StationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAllRecords(){
        LOG.info("showAllRecords");
        List<StationData> list = stationService.records();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stations");
        modelAndView.addObject("stationDataList", list);
        return modelAndView;
    }

    //    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    public @ResponseBody
//    String handleFileUpload(@RequestParam("file") MultipartFile file) {
//
//        if (!file.isEmpty()) {
//            try {
//                byte[] fileBytes = file.getBytes();
//                String rootPath = System.getProperty("catalina.home");
//                LOG.info("Server rootPath: " + rootPath);
//                LOG.info("File original name: " + file.getOriginalFilename());
//                LOG.info("File content type: " + file.getContentType());
//
//                File newFile = new File(rootPath + File.separator + file.getOriginalFilename());
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
//                stream.write(fileBytes);
//                stream.close();
//
//                Data data = new ObjectMapper().readValue(newFile, Data.class);
//                for(StationData sd: data.getStationData())
//
//                LOG.info(data);
//
//                LOG.info("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
//                return "File is saved under: " + rootPath + File.separator + file.getOriginalFilename();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                LOG.error(e.getStackTrace());
//                return "File upload is failed: " + e.getMessage();
//            }
//        } else {
//            LOG.error("File upload is failed: File is empty");
//            return "File upload is failed: File is empty";
//        }
//    }
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        if (!file.isEmpty()) {
            try {
                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                LOG.info("Server rootPath: " + rootPath);
                LOG.info("File original name: " + file.getOriginalFilename());
                LOG.info("File content type: " + file.getContentType());

                File newFile = new File(rootPath + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
                stream.write(fileBytes);
                stream.close();

                Data data = new ObjectMapper().readValue(newFile, Data.class);
                for(StationData sd: data.getStationData())
                    stationService.add(sd);

                LOG.info("Записи добавлены в БД...");
                LOG.info("File is saved under: " + rootPath + File.separator + file.getOriginalFilename());
                return modelAndView;

            } catch (Exception e) {
                e.printStackTrace();
                LOG.error(e.getStackTrace());
                LOG.error("File upload is failed: " + e.getMessage());
                return modelAndView;
            }
        } else {
            LOG.error("File upload is failed: File is empty");
            return modelAndView;
        }
    }
}

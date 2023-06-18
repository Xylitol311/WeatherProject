package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해 DB에 일기 저장") // value : API의 한줄 설명. notes : API의 긴 설명이 필요할 때 사용.
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "저장할 날짜", example = "2020-02-02") LocalDate date, @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터 가져오기")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 날짜", example = "2020-02-02") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간의 모든 일기 데이터 가져오기")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 첫번째날", example = "2020-02-02")
                            LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 마지막날", example = "2020-02-02")
                            LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 다이어리 수정")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "수정할 다이어리의 날짜", example = "2020-02-02") LocalDate date, @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 다이어리 삭제")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "삭제할 다이어리의 날짜", example = "2020-02-02") LocalDate date) {
        diaryService.deleteDiary(date);
    }
}

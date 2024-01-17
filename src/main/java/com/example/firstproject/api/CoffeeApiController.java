package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    CoffeeRepository coffeeRepository;

    // 조회
    @GetMapping("/api/coffees")
    public ArrayList<Coffee> index(){
        return coffeeRepository.findAll();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }

    // 생성
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeForm dto){
        Coffee coffeeEntity = dto.toEntity();
        return coffeeRepository.save(coffeeEntity);
    }
    // 수정
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> patch(@PathVariable Long id, @RequestBody CoffeeForm dto){
        // http 로 받은정보 엔티티로 변환
        Coffee coffeeEntity = dto.toEntity();
        // 데이터베이스에서 바꿀 타깃 찾기
        Coffee target = coffeeRepository.findById(coffeeEntity.getId()).orElse(null);
        // 오류 났을때
        if(target==null || id!=coffeeEntity.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 정상작동할때
        target.patch(coffeeEntity);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}















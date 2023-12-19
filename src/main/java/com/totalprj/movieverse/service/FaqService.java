package com.totalprj.movieverse.service;

import com.totalprj.movieverse.dto.FaqDto;
import com.totalprj.movieverse.entity.Faq;
import com.totalprj.movieverse.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;



    // faq 추가 기능 만들기
    public FaqDto createFaq(String faqAdd) {
        // Faq 객체 생성
        Faq faq = new Faq();
        faq.setFaqAnswer(faqAdd);
        faq.setFaqQuestion(faqAdd);
        // Faq 저장
        Faq saved = faqRepository.save(faq);
        // 저장된 Faq를 Dto로 변환
        FaqDto faqDto = convertEntityToDto(saved);
        log.info("savedFaq : {}", faqDto);
        return faqDto;
    }

    //엔티티를 Dto로 변환
    public FaqDto convertEntityToDto(Faq faq){
        FaqDto faqDto = new FaqDto();
        faqDto.setFaqAnswer(faq.getFaqAnswer());
        faqDto.setFaqQuestion(faq.getFaqQuestion());
        return faqDto;
    }
}

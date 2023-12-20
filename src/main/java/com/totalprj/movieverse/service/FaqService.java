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



    // faq 추가
    public boolean createFaq(FaqDto faqDto) {
        // Faq 객체 생성
        Faq faq = new Faq();
        faq.setFaqAnswer(faqDto.getFaqAnswer());
        faq.setFaqQuestion(faqDto.getFaqQuestion());
        // Faq 저장
        Faq saved = faqRepository.save(faq);
        // 저장된 Faq를 Dto로 변환

        log.info("savedFaq : {}", faqDto);
        return true;
    }


    // faq 수정
    public boolean reviseFaq(FaqDto faqDto) {
        try {
            Faq faq = faqRepository.findById(faqDto.getFaqId()).orElseThrow(
                    ()->new RuntimeException("수정할 게시글이 없습니다.")
            );

            // 수정할 내용을 설정
            faq.setFaqAnswer(faqDto.getFaqAnswer());
            faq.setFaqQuestion(faqDto.getFaqQuestion());

        // 수정된 Faq 저장
        Faq saved = faqRepository.save(faq);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // faq 삭제
    public boolean deleteFaq(Long faqId){
        try {
            Faq faq = faqRepository.findById(faqId)
                    .orElseThrow(()->new RuntimeException("해당 게시글이 존재하지 않습니다"));

            faqRepository.delete(faq);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    //엔티티를 Dto로 변환
    public FaqDto convertEntityToDto(Faq faq){
        FaqDto faqDto = new FaqDto();
        faqDto.setFaqId(faq.getFaqId());
        faqDto.setFaqAnswer(faq.getFaqAnswer());
        faqDto.setFaqQuestion(faq.getFaqQuestion());
        return faqDto;
    }
}

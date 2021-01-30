package ru.manasyan.advertising.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.data.entities.Request;
import ru.manasyan.advertising.repository.BannerRepository;
import ru.manasyan.advertising.repository.RequestRepository;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RequestService {
    private final RequestRepository requestRepository;

    private final BannerRepository bannerRepository;

    public Set<Banner> findTodaysUserBanners(
            String categoryRequestName,
            String userAgent,
            String ipAddress
    ) {
        return bannerRepository.findTodaysBannersByUserAgentAndIp(
                userAgent,
                ipAddress,
                categoryRequestName
        );
    }

    public void create(
            Banner banner,
            String userAgent,
            String ipAddress
    ) {
        requestRepository.save(
                Request.builder()
                        .banner(banner)
                        .date(LocalDateTime.now())
                        .userAgent(userAgent)
                        .ipAddress(ipAddress)
                        .build()
        );
    }
}

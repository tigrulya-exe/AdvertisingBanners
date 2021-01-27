package ru.manasyan.advertising.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.manasyan.advertising.data.dto.BannerDto;
import ru.manasyan.advertising.data.dto.ContentDto;
import ru.manasyan.advertising.data.entities.Banner;
import ru.manasyan.advertising.mapper.Mapper;
import ru.manasyan.advertising.service.BannerService;
import ru.manasyan.advertising.service.CrudService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/banners")
public class BannerController extends AbstractCrudController<Banner, BannerDto> {
    public BannerController(
            CrudService<Banner> service,
            Mapper<Banner, BannerDto> mapper) {
        super(service, mapper);
    }

    @GetMapping
    public ContentDto getByCategory(
            @RequestParam("category") String categoryName,
            HttpServletRequest request) {
        BannerService service = (BannerService) getService();
        Banner banner = service.getByCategory(
                categoryName,
                request.getHeader(HttpHeaders.USER_AGENT),
                request.getRemoteAddr()
        );
        return new ContentDto(banner.getName());
    }
}

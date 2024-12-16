package kattsyn.dev.ApartmentsUnderConstruction.service;

import kattsyn.dev.ApartmentsUnderConstruction.entities.Image;
import kattsyn.dev.ApartmentsUnderConstruction.repositories.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> fromStringToImageList(List<String> urls) {
        return urls.stream().map(url -> {
                    Image image = new Image();
                    image.setUrl(url);
                    return image;
                }
        ).toList();
    }

    public List<Image> saveAll(List<Image> images) {
        return imageRepository.saveAll(images);
    }

    public List<Image> saveAllByUrls(List<String> urls) {
        return saveAll(fromStringToImageList(urls));
    }

}

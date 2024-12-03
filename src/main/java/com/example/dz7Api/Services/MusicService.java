import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dz7Api.Models.Music;
import com.example.dz7Api.Repositories.MusicRepository;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public Music getMusicByArtist(String musicName) {
        return musicRepository.findByMusicName(musicName);
    }

    public Music saveMusic(Music music) {
        return musicRepository.save(music);
    }

    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElse(null);
    }
}

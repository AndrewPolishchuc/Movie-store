package movie.storage.service.impl;

import java.util.List;
import movie.storage.dao.CinemaHallDao;
import movie.storage.lib.Inject;
import movie.storage.lib.Service;
import movie.storage.model.CinemaHall;
import movie.storage.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}

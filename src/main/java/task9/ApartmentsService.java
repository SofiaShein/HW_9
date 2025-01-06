package task9;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentsService {
    private final ApartmentsRepository apartmentsRepository;


    public ApartmentsService(ApartmentsRepository apartmentsRepository) {
        this.apartmentsRepository = apartmentsRepository;
    }

    public Apartment getApartmentById(Long id) {
        return apartmentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Apartment with id " + id + " not found"));
    }


    @Transactional(readOnly = true)
    public Optional<Apartment> findById(long id) {
        return apartmentsRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Apartment findApartmentById(long id) {
        return apartmentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Apartment not found"));
    }

    @Transactional(readOnly = true)
    public long count() {
        return apartmentsRepository.count();
    }

    @Transactional
    public void addApartment(Apartment apartment) {
        apartmentsRepository.save(apartment);
    }

    public void deleteApartment(long[] idList) {
        for (long id : idList) {
            apartmentsRepository.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    public List<Apartment> findAll(Pageable pageable) {
        return apartmentsRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Apartment> findByPrice (double bottom, double top, Pageable pageable) {
        return apartmentsRepository.findByPrice(bottom, top, pageable);
    }

    @Transactional
    public void updateApartment(String district, String address, double area, int rooms, double price, long id) {
        apartmentsRepository.updateApartment(district, address, area, rooms, price, id);
    }

}
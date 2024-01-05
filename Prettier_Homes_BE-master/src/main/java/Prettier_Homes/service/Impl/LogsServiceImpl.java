package Prettier_Homes.service.Impl;



import Prettier_Homes.converter.LogsMapper;
import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.LogsEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.enums.EnmLog;
import Prettier_Homes.data.repository.LogsRepository;
import Prettier_Homes.dto.LogsDto;
import Prettier_Homes.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    LogsRepository repository;
    @Autowired
    LogsMapper mapper;

    @Override
    public ResponseEntity<List<LogsDto>> getLogsByUserId(Long userId) {
        List<LogsEntity> entities = repository.findByUserId(userId);
        List<LogsDto> dtos= mapper.toDto(entities);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public void createLog(AdvertsEntity entity, UserEntity user, EnmLog status) {
        LogsEntity logsEntity =new LogsEntity();
        logsEntity.setCreateAt(LocalDateTime.now());
        logsEntity.setLog(status);
        logsEntity.setAdvert(entity);
        logsEntity.setUser(user);
        repository.save(logsEntity);

    }

}
























/*

loglama, bir metot çalıştığında o metot hakkında kayıt tutmayı ifade eder. Bu, ilgili metotun hangi parametrelerle çağrıldığını,
hangi değeri döndürdüğünü ve herhangi bir hata durumunda hatanın ayrıntılarını kaydetmek için kullanılır.
Aşağıda, bir metodu loglama örneği gösterilmektedir:

public void exampleMethod(int param1, String param2) {
    // Metodun çalıştığında log düzeyine göre bilgilendirme yapılır
    System.out.println("exampleMethod is called with param1: " + param1 + " and param2: " + param2);

    try {
        // Metodun gerçekleştirdiği işlemler
        // ...

        // İşlemler başarıyla tamamlandığında log düzeyine göre bilgilendirme yapılır
        System.out.println("exampleMethod successfully completed");
    } catch (Exception e) {
        // Hata durumunda log düzeyine göre hata ayrıntıları kaydedilir
        System.err.println("An error occurred in exampleMethod: " + e.getMessage());
    }
}
Yukarıdaki örnekte, exampleMethod adında bir metot tanımlanmıştır. Metodun içinde parametrelerin değerleri, metodun çalıştığı
bilgilendirme amacıyla loglama kullanılarak kaydedilir. Ardından, try-catch blokları arasında gerçekleştirilen işlemler yer almaktadır.
 Başarılı bir şekilde tamamlandığı durumlarda bir bilgilendirme mesajı kaydedilir. Bunun yanında, bir hata durumunda try bloğunun
 içindeki işlemler hataya neden olursa hatanın ayrıntıları loglama kullanılarak kaydedilir.
Loglama, daha kompleks senaryolarda dosyaya, veritabanına veya loglama kütüphanelerine kaydetme gibi farklı tekniklerle de yapılabilir.
Bu örnekte basit bir şekilde konsola yazdırma işlemi uygulanmıştır.
 */



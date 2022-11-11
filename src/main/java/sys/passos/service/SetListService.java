package sys.passos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sys.passos.dao.SetListRepository;
import sys.passos.dao.UserRepository;
import sys.passos.dto.SetListDTO;
import sys.passos.dto.UserDTO;
import sys.passos.exception.SetListNotFoundException;
import sys.passos.exception.UserNotFoundException;
import sys.passos.model.SetList;
import sys.passos.model.User;
import sys.passos.util.CopyProperties;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SetListService {

    @Autowired
    private SetListRepository setListRepository;

    public SetListDTO getSetList() {
        List<SetList> setList = setListRepository.findAll();
        return (SetListDTO) setList;
    }

    public SetListDTO getSetListById(Long id) {
        Optional<SetList> setList = setListRepository.findById(id);
        return setList.map(u -> CopyProperties.copy(u, SetListDTO.class)).orElseThrow(() -> new SetListNotFoundException("SetList Not Found"));
    }

    public SetListDTO insert(SetList setList) {
        Assert.isNull(setList.getId(),"Cannot add SetList");
        return CopyProperties.copy(setListRepository.save(setList), SetListDTO.class);
    }

    public SetListDTO update(SetList setList, Long id) {
        Assert.notNull(id,"Cannot update SetList");

        Optional<SetList> optional = setListRepository.findById(id);
        if(optional.isPresent()) {
            SetList db = optional.get();
            db.setId(setList.getId());
            System.out.println("SetList id " + db.getId());
            setListRepository.save(db);
            return CopyProperties.copy(setListRepository.save(setList), SetListDTO.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        setListRepository.deleteById(id);
    }
}

package br.com.uniamerica.api.service;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Optional<Agenda> findBy(Long id){return this.agendaRepository.findById(id);}

    public Page<Agenda> listAll(Pageable pageable){return this.agendaRepository.findAll(pageable);}

    @Transactional
    public void update (Long id , Agenda agenda){
        if (id == agenda.getId()){
            this.saveTransactional(agenda);
            this.validarFormulario(agenda);
        }
        else{
            throw new RuntimeException();
        }
    }

    @Transactional
    public void saveTransactional(Agenda agenda){this.agendaRepository.save(agenda);}

    public void validarFormulario(Agenda agenda){
        if(agenda.getEncaixe() == null){
            throw new RuntimeException("erro no agendamento encaixe nulo");
        }
        if(agenda.getData() == null){
            throw new RuntimeException("erro no agendamento dada nula");
        }
        if(agenda.getMedico() == null){
            throw new RuntimeException("erro no agendamento, medico nulo");
        }
        if(agenda.getPaciente() == null){
            throw new RuntimeException("erro no agendamento, paciente nulo");
        }
        if(agenda.getStatus() == null){
            throw new RuntimeException("erro no agendamento");
        }

    }

    @Transactional
    public void insert(Agenda agenda){this.agendaRepository.save(agenda);}
    
    @Transactional
    public void updateDataExcluido(Long id, Agenda agenda){
        if (id == agenda.getId()){
            this.agendaRepository.updateStatus(LocalDateTime.now(),agenda.getId());
        }
    }
}
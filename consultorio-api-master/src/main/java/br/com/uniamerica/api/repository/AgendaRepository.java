package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Modifying
    @Query("UPDATE Agenda agenda " +
            "SET agenda.excluido = :excluido " +
            "WHERE agenda.id = :agenda")
    public void updateStatus(@Param("excluido") LocalDateTime excluido, @Param("agenda") Long idAgenda);

}

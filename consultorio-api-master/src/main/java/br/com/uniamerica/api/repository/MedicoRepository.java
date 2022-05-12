package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;



@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Modifying
    @Query("UPDATE Medico medico " +
            "SET Medico.excluido = :excluido " +
            "WHERE Medico.id = :Medico")
    public void updateStatus(@Param("excluido") LocalDateTime excluido, @Param("Medico") Long idMedico);

}



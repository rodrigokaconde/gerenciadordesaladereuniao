package br.com.rodrigokaconde.saladereuniao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigokaconde.saladereuniao.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}

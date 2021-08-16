package br.com.rodrigokaconde.saladereuniao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity//mostra que é uma classe de configuração da tabela
@Table(name="meetingroom")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//id gerado automatico
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="date", nullable = false)
    private String date;

    @Column(name="startHour", nullable = false)
    private String startHour;

    @Column(name="endHour", nullable = false)
    private String endHour;

    @Override
    public String toString(){
        return "Room [ id="+id+",name="+name+",startHour="+startHour+",endHour="+endHour+" ]";
    }
}

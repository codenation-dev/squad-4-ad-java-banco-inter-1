package br.com.centraldeerros.centraldeerro.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class ErroDesenvolvimento extends Erro{

    public ErroDesenvolvimento(){
        super();
    }
}

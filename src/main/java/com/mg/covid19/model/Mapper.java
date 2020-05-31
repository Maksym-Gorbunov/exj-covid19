package com.mg.covid19.model;

import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.model.model.StatisticModel;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Mapper extends ModelMapper {


    public <E, M> M entityToModel(E entity){
        if(entity.getClass().equals(Statistic.class)){
            return this.map(entity, (Type) StatisticModel.class);
        }
        if(entity.getClass().equals(Code.class)){
            return this.map(entity, (Type) CodeModel.class);
        }
        return null;
    }


    public <E, M> List<M> entitiesToModels(List<E> entities){
        if(entities.get(0).getClass().equals(Statistic.class)){
            List<StatisticModel> models = new ArrayList<>();
            for(E entity : entities){
                models.add(entityToModel(entity));
            }
            return (List<M>) models;
        }
        if(entities.get(0).getClass().equals(Code.class)){
            List<CodeModel> models = new ArrayList<>();
            for(E entity : entities){
                models.add(entityToModel(entity));
            }
            return (List<M>) models;
        }
        return null;
    }


    public <M, E> E modelToEntity(M model){
        if(model.getClass().equals(StatisticModel.class)){
            return this.map(model, (Type) Statistic.class);
        }
        if(model.getClass().equals(CodeModel.class)){
            return this.map(model, (Type) Code.class);
        }
        return null;
    }


    public <M, E> List<E> modelsToEntities(List<M> models){
        if(models.get(0).getClass().equals(StatisticModel.class)){
            List<Statistic> entities = new ArrayList<>();
            for(M model : models){
                entities.add(modelToEntity(model));
            }
            return (List<E>) entities;
        }
        if(models.get(0).getClass().equals(CodeModel.class)){
            List<Code> entities = new ArrayList<>();
            for(M model : models){
                entities.add(modelToEntity(model));
            }
            return (List<E>) entities;
        }
        return null;
    }
}

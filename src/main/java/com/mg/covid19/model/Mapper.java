package com.mg.covid19.model;

import com.mg.covid19.model.entity.Code;
import com.mg.covid19.model.entity.Country;
import com.mg.covid19.model.entity.Province;
import com.mg.covid19.model.entity.Statistic;
import com.mg.covid19.model.model.CodeModel;
import com.mg.covid19.model.model.CountryModel;
import com.mg.covid19.model.model.ProvinceModel;
import com.mg.covid19.model.model.StatisticModel;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Mapper extends ModelMapper {


    public <E, M> M toModel(E entity){
        if(entity.getClass().getSimpleName().startsWith("Country")){
            return this.map(entity, (Type) CountryModel.class);
        }
        if(entity.getClass().getSimpleName().startsWith("Statistic")){
            return this.map(entity, (Type) StatisticModel.class);
        }
        if(entity.getClass().getSimpleName().startsWith("Code")){
            return this.map(entity, (Type) CodeModel.class);
        }
        if(entity.getClass().getSimpleName().startsWith("Province")){
            return this.map(entity, (Type) ProvinceModel.class);
        }
        return null;
    }


    public <E, M> List<M> toModels(List<E> entities){
        if(entities.get(0).getClass().getSimpleName().startsWith("Country")){
            List<CountryModel> models = new ArrayList<>();
            for(E entity : entities){
                models.add(toModel(entity));
            }
            return (List<M>) models;
        }
        if(entities.get(0).getClass().getSimpleName().startsWith("Statistic")){
            List<StatisticModel> models = new ArrayList<>();
            for(E entity : entities){
                models.add(toModel(entity));
            }
            return (List<M>) models;
        }
        if(entities.get(0).getClass().getSimpleName().startsWith("Code")){
            List<CodeModel> models = new ArrayList<>();
            for(E entity : entities){
                models.add(toModel(entity));
            }
            return (List<M>) models;
        }
        if(entities.get(0).getClass().getSimpleName().startsWith("Province")){
            List<ProvinceModel> models = new ArrayList<>();
            for(E entity : entities){
                models.add(toModel(entity));
            }
            return (List<M>) models;
        }
        return null;
    }


    public <M, E> E toEntity(M model){
        if(model.getClass().getSimpleName().startsWith("CountryModel")){
            return this.map(model, (Type) Country.class);
        }
        if(model.getClass().getSimpleName().startsWith("StatisticModel")){
            return this.map(model, (Type) Statistic.class);
        }
        if(model.getClass().getSimpleName().startsWith("CodeModel")){
            return this.map(model, (Type) Code.class);
        }
        if(model.getClass().getSimpleName().startsWith("ProvinceModel")){
            return this.map(model, (Type) Province.class);
        }
        return null;
    }


    public <M, E> List<E> toEntities(List<M> models){
        if(models.get(0).getClass().getSimpleName().startsWith("CountryModel")){
            List<Country> entities = new ArrayList<>();
            for(M model : models){
                entities.add(toEntity(model));
            }
            return (List<E>) entities;
        }
        if(models.get(0).getClass().getSimpleName().startsWith("StatisticModel")){
            List<Statistic> entities = new ArrayList<>();
            for(M model : models){
                entities.add(toEntity(model));
            }
            return (List<E>) entities;
        }
        if(models.get(0).getClass().getSimpleName().startsWith("CodeModel")){
            List<Code> entities = new ArrayList<>();
            for(M model : models){
                entities.add(toEntity(model));
            }
            return (List<E>) entities;
        }
        if(models.get(0).getClass().getSimpleName().startsWith("ProvinceModel")){
            List<Province> entities = new ArrayList<>();
            for(M model : models){
                entities.add(toEntity(model));
            }
            return (List<E>) entities;
        }
        return null;
    }
}

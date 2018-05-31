package com.outfittery.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelDTO {


    @JsonIgnore
    private Long id;

    @NotNull(message = "Text can not be null!")
    private String text;

    private ZonedDateTime publishDate;

    public ModelDTO() {
    }

    public ModelDTO(Long id, String text, ZonedDateTime publishDate) {
        this.id = id;
        this.text = text;
        this.publishDate = publishDate;
    }

    public static ModelDTOBuilder newBuilder() {
        return new ModelDTOBuilder();
    }


    @JsonProperty
    public Long getId() {
        return id;
    }


    public String getText() {
        return text;
    }

    @JsonProperty
    public String getPublishDate() {
        return publishDate != null ? publishDate.toString() : null;
    }

    @JsonIgnore
    public ZonedDateTime getPublishDateTime() {
        return publishDate;
    }


    public static class ModelDTOBuilder {
        private Long id;
        private String text;
        private ZonedDateTime publishDate;


        public ModelDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }


        public ModelDTOBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public ModelDTOBuilder setPublishDate(ZonedDateTime publishDate) {
            this.publishDate = publishDate;
            return this;
        }


//        public ModelDTOBuilder setKeywords(Set<String> keywords) {
//            this.keywords = CollectionsUtil.toLowerCase(keywords);
//            return this;
//        }



        public ModelDTO createModelDTO() {
            return new ModelDTO(id,text, publishDate);
        }

    }
}

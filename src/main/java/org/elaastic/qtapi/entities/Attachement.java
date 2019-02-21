package org.elaastic.qtapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Attachement {

    private static final String[] TYPES_MIME_IMG_DISPLAYABLE = {"image/gif","image/jpeg","image/png"};

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String path;
    @NotNull
    private String name;

    @Column(name="original_name")
    private String originalName;
    private int size;
    @Column(name="dimension_height")
    private int dimensionHeight;
    @Column(name="dimension_width")
    private int dimensionWidth;
    @Column(name="type_mime")
    private String typeMime;

    @ManyToOne
    private Statement statement;

    @Column(name="to_delete")
    private Boolean toDelete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachement that = (Attachement) o;
        return id == that.id &&
                getSize() == that.getSize() &&
                getDimensionHeight() == that.getDimensionHeight() &&
                getDimensionWidth() == that.getDimensionWidth() &&
                getPath().equals(that.getPath()) &&
                getName().equals(that.getName()) &&
                Objects.equals(getOriginalName(), that.getOriginalName()) &&
                Objects.equals(getTypeMime(), that.getTypeMime()) &&
                Objects.equals(getStatement(), that.getStatement()) &&
                Objects.equals(getToDelete(), that.getToDelete());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public int getDimensionHeight() {
        return dimensionHeight;
    }

    public void setDimensionHeight(int dimensionHeight) {
        this.dimensionHeight = dimensionHeight;
    }

    public int getDimensionWidth() {
        return dimensionWidth;
    }

    public void setDimensionWidth(int dimensionWidth) {
        this.dimensionWidth = dimensionWidth;
    }

    public String getTypeMime() {
        return typeMime;
    }

    public void setTypeMime(String typeMime) {
        this.typeMime = typeMime;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Boolean getToDelete() {
        return toDelete;
    }

    public void setToDelete(Boolean toDelete) {
        this.toDelete = toDelete;
    }
}

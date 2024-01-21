package org.example.risprojekatv2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PracenjeId implements Serializable {
    private static final long serialVersionUID = -7012351232948667738L;
    @NotNull
    @Column(name = "pratioc_id", nullable = false)
    private Integer pratiocId;

    @NotNull
    @Column(name = "pratilac_id", nullable = false)
    private Integer pratilacId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PracenjeId entity = (PracenjeId) o;
        return Objects.equals(this.pratiocId, entity.pratiocId) &&
                Objects.equals(this.pratilacId, entity.pratilacId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pratiocId, pratilacId);
    }

}
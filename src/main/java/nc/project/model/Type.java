package nc.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "types")
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String type;
}

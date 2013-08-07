package hidden;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity public class HiddenPart {
	@Getter @Setter @Id @GeneratedValue Integer id;
	@Getter @Setter @ManyToMany List<HiddenBody> hiddenBodys;
	@Getter @Setter String str;
}

package hidden;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity public class HiddenBody {
	@Getter @Setter @Id @GeneratedValue Integer id;
	@Getter @Setter @OneToMany List<HiddenArm> hiddenArms;
	@Getter @Setter @ManyToMany List<HiddenPart> hiddenParts;
	@Getter @Setter String str;
}

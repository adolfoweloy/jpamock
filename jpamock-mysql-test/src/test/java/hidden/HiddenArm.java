package hidden;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity public class HiddenArm {
	@Getter @Setter @Id @GeneratedValue Integer id;
	@Getter @Setter @ManyToOne HiddenBody hiddenBody;
	@Getter @Setter String str;
}

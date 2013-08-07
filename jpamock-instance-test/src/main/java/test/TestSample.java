package test;

import jpamock.instance.JPAMock;
import junit.framework.TestCase;
import sample.Body;
import sample.Finger;
import sample.Foot;

public class TestSample extends TestCase {

	JPAMock jpaMock = new JPAMock(null);

	public void testBidirectional() {
		Body body = jpaMock.mock(Body.class);
		Finger finger = body.arms.get(0).hand.fingers.get(0);
		Foot foot = body.legs.get(0).foot;
		assertNotNull(finger);
		assertNotNull(foot);
		assertSame(body, finger.hand.arm.body);
	}
//
//	public void testOverrideClass() {
//		Arm arm = new Arm();
//		arm.size = 8;
//		Body body = jpaMock.when(Arm.class).thenInject(arm).mock(Body.class);
//		assertEquals(8, body.arms.get(0).size.intValue());
//		assertNotNull(body.arms.get(0).hand);
//	}
//
//	public void testOverrideArray() {
//		Arm arm1 = new Arm();
//		arm1.size = 1;
//		Arm arm2 = new Arm();
//		arm2.size = 2;
//		Body body = jpaMock.when(Body.class, "arms").thenInject(Arrays.asList(arm1, arm2)).mock(Body.class);
//		assertEquals(1, body.arms.get(0).size.intValue());
//		assertNotNull(body.arms.get(0).hand);
//		assertEquals(2, body.arms.get(1).size.intValue());
//		assertNotNull(body.arms.get(1).hand);
//		assertSame(arm1, body.arms.get(0));
//		assertSame(arm2, body.arms.get(1));
//	}
}

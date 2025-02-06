package tech.intellispaces.core.specification.traverse;

import org.junit.jupiter.api.Test;
import tech.intellispaces.core.specification.exception.TraversePathSpecificationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for class {@link TraversePathParseFunctions}.
 */
public class TraversePathParseFunctionsTest {

  @Test
  public void testParse_whenEmpty() {
    assertThatThrownBy(() -> TraversePathParseFunctions.parse(""))
        .isExactlyInstanceOf(TraversePathSpecificationException.class)
        .hasMessage("Traverse path source is not defined: ''");
  }

  @Test
  public void testParse_whenTo() {
    assertThatThrownBy(() -> TraversePathParseFunctions.parse("to"))
        .isExactlyInstanceOf(TraversePathSpecificationException.class)
        .hasMessage("Traverse path source is not defined: 'to'");
  }

  @Test
  public void testParse_whenThru() {
    assertThatThrownBy(() -> TraversePathParseFunctions.parse("thru"))
        .isExactlyInstanceOf(TraversePathSpecificationException.class)
        .hasMessage("Traverse path source is not defined: 'thru'");
  }

  @Test
  public void testParse_whenSuper() {
    assertThatThrownBy(() -> TraversePathParseFunctions.parse("super"))
        .isExactlyInstanceOf(TraversePathSpecificationException.class)
        .hasMessage("Traverse path source is not defined: 'super'");
  }

  @Test
  public void testParse_whenThereIsSourceOnly() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).isEmpty();
  }

  @Test
  public void testParse_whenOneTransitionTo() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this to Domain1");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).hasSize(1);
    assertThat(path.transitions().get(0).isToTransition()).isTrue();
    assertThat(path.transitions().get(0).asToTransition().isSuperDomain()).isFalse();
    assertThat(path.transitions().get(0).asToTransition().domain().name()).isEqualTo("Domain1");
  }

  @Test
  public void testParse_whenOneTransitionThru() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this thru channel1");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).hasSize(1);
    assertThat(path.transitions().get(0).isThruTransition()).isTrue();
    assertThat(path.transitions().get(0).asThruTransition().channel().name()).isEqualTo("channel1");
  }

  @Test
  public void testParse_whenOneTransitionToSuper() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this to super Domain1");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).hasSize(1);
    assertThat(path.transitions().get(0).isToTransition()).isTrue();
    assertThat(path.transitions().get(0).asToTransition().isSuperDomain()).isTrue();
    assertThat(path.transitions().get(0).asToTransition().domain().name()).isEqualTo("Domain1");
  }

  @Test
  public void testParse_whenTwoTransitionTo() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this to Domain1 to Domain2");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).hasSize(2);
    assertThat(path.transitions().get(0).isToTransition()).isTrue();
    assertThat(path.transitions().get(0).asToTransition().isSuperDomain()).isFalse();
    assertThat(path.transitions().get(0).asToTransition().domain().name()).isEqualTo("Domain1");
    assertThat(path.transitions().get(1).isToTransition()).isTrue();
    assertThat(path.transitions().get(1).asToTransition().isSuperDomain()).isFalse();
    assertThat(path.transitions().get(1).asToTransition().domain().name()).isEqualTo("Domain2");
  }

  @Test
  public void testParse_whenTwoTransitionThru() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this thru channel1 thru channel2");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).hasSize(2);
    assertThat(path.transitions().get(0).isThruTransition()).isTrue();
    assertThat(path.transitions().get(0).asThruTransition().channel().name()).isEqualTo("channel1");
    assertThat(path.transitions().get(1).isThruTransition()).isTrue();
    assertThat(path.transitions().get(1).asThruTransition().channel().name()).isEqualTo("channel2");
  }

  @Test
  public void testParse_whenTransitionToAndThenTransitionThru() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this to Domain1 thru channel1");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).hasSize(2);
    assertThat(path.transitions().get(0).isToTransition()).isTrue();
    assertThat(path.transitions().get(0).asToTransition().isSuperDomain()).isFalse();
    assertThat(path.transitions().get(0).asToTransition().domain().name()).isEqualTo("Domain1");
    assertThat(path.transitions().get(1).isThruTransition()).isTrue();
    assertThat(path.transitions().get(1).asThruTransition().channel().name()).isEqualTo("channel1");
  }

  @Test
  public void testParse_whenTransitionThruAndThenTransitionTo() throws Exception {
    TraversePathSpecification path = TraversePathParseFunctions.parse("this thru channel1 to Domain1");

    assertThat(path.sourceDomain().name()).isEqualTo("this");
    assertThat(path.transitions()).hasSize(2);
    assertThat(path.transitions().get(0).isThruTransition()).isTrue();
    assertThat(path.transitions().get(0).asThruTransition().channel().name()).isEqualTo("channel1");
    assertThat(path.transitions().get(1).isToTransition()).isTrue();
    assertThat(path.transitions().get(1).asToTransition().isSuperDomain()).isFalse();
    assertThat(path.transitions().get(1).asToTransition().domain().name()).isEqualTo("Domain1");
  }
}

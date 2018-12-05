package reactivefeign.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Measurement(iterations = 10, time = 1)
@Warmup(iterations = 5, time = 1)
@Fork(3)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class RealSingleRequestBenchmarks extends RealRequestBenchmarks{

    @Setup
    public void setup() throws Exception {
        super.setup();
    }

    @TearDown
    public void tearDown() throws Exception {
        super.tearDown();
    }

  /**
   * How fast can we execute get commands synchronously in parallel using Feign?
   */
  @Benchmark
  public String query_feign() {
    return feign.justGet();
  }

  /**
   * How fast can we execute get commands in parallel using reactive web client based Feign?
   */
  @Benchmark
  public String query_webClientFeign() {
    return webClientFeign.justGet().block();
  }

  /**
   * How fast can we execute get commands in parallel using reactive Jetty based Feign?
   */
  @Benchmark
  public String query_jettyFeign() {
    return jettyFeign.justGet().block();
  }

}
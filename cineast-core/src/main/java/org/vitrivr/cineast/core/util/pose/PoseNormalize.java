package org.vitrivr.cineast.core.util.pose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.vitrivr.cineast.core.data.FloatVectorImpl;
import org.vitrivr.cineast.core.data.segments.SegmentContainer;

public class PoseNormalize {
  static public void minMaxScale(float[][] poseArr) {
    float minX = Float.POSITIVE_INFINITY;
    float maxX = Float.NEGATIVE_INFINITY;
    float minY = Float.POSITIVE_INFINITY;
    float maxY = Float.NEGATIVE_INFINITY;
    for (float[] xyc : poseArr) {
      if (xyc[0] < minX) {
        minX = xyc[0];
      }
      if (xyc[0] > maxX) {
        maxX = xyc[0];
      }
      if (xyc[1] < minY) {
        minY = xyc[1];
      }
      if (xyc[1] > maxY) {
        maxY = xyc[1];
      }
    }
    float rngX = maxX - minX;
    float rngY = maxY - minY;
    for (float[] xyc : poseArr) {
      xyc[0] = (float) ((xyc[0] - minX) / rngX - 0.5);
      xyc[1] = (float) ((xyc[1] - minY) / rngY - 0.5);
    }
  }

  static public float[] flatten(float[][] poseArr) {
    float[] flatArr = new float[2 * poseArr.length];
    for (int idx = 0; idx < poseArr.length; idx++) {
      flatArr[2 * idx] = poseArr[idx][0];
      flatArr[2 * idx + 1] = poseArr[idx][1];
    }
    return flatArr;
  }

  static public Optional<float[]> pipeline(PoseSpec spec, float[][] pose) {
    float[][] subsetPose = spec.subset(pose);
    PoseNormalize.minMaxScale(subsetPose);
    return Optional.of(PoseNormalize.flatten(subsetPose));
  }

  static public Stream<float[]> procPoses(PoseSpec spec, float[][][] poses) {
    return Arrays.stream(poses).flatMap(pose -> {
      Optional<float[]> flatPose = PoseNormalize.pipeline(spec, pose);
      return flatPose.<Stream<? extends float[]>>map(Stream::of).orElseGet(Stream::empty);
    });
  }
}

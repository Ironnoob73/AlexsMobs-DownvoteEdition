package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityRhinoceros;
import com.github.alexthe666.alexsmobs.entity.util.Maths;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;

public class ModelRhinoceros extends AdvancedEntityModel<EntityRhinoceros> {
    private final AdvancedModelBox root;
    private final AdvancedModelBox body;
    private final AdvancedModelBox leftLeg;
    private final AdvancedModelBox rightLeg;
    private final AdvancedModelBox chest;
    private final AdvancedModelBox head;
    private final AdvancedModelBox horns;
    private final AdvancedModelBox leftEar;
    private final AdvancedModelBox rightEar;
    private final AdvancedModelBox leftArm;
    private final AdvancedModelBox rightArm;
    private final ModelAnimator animator;

    public ModelRhinoceros() {
        texWidth = 128;
        texHeight = 128;

        root = new AdvancedModelBox(this, "root");
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this, "body");
        body.setRotationPoint(0.0F, -19.0F, 4.0F);
        root.addChild(body);
        body.setTextureOffset(0, 44).addBox(-9.0F, -10.0F, -6.0F, 18.0F, 20.0F, 21.0F, 0.0F, false);

        leftLeg = new AdvancedModelBox(this, "leftLeg");
        leftLeg.setRotationPoint(6.0F, 9.0F, 12.0F);
        body.addChild(leftLeg);
        leftLeg.setTextureOffset(70, 77).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 11.0F, 9.0F, 0.0F, false);

        rightLeg = new AdvancedModelBox(this, "rightLeg");
        rightLeg.setRotationPoint(-6.0F, 9.0F, 12.0F);
        body.addChild(rightLeg);
        rightLeg.setTextureOffset(70, 77).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 11.0F, 9.0F, 0.0F, true);

        chest = new AdvancedModelBox(this, "chest");
        chest.setRotationPoint(0.0F, -4.0F, -10.0F);
        body.addChild(chest);
        chest.setTextureOffset(0, 0).addBox(-11.0F, -10.0F, -14.0F, 22.0F, 23.0F, 20.0F, 0.0F, false);

        head = new AdvancedModelBox(this, "head");
        head.setRotationPoint(0.0F, 3.0F, -14.0F);
        chest.addChild(head);
        setRotationAngle(head, 0.3927F, 0.0F, 0.0F);
        head.setTextureOffset(76, 35).addBox(-6.0F, -6.0F, -8.0F, 12.0F, 14.0F, 9.0F, 0.0F, false);
        head.setTextureOffset(65, 0).addBox(-4.0F, 0.0F, -18.0F, 8.0F, 8.0F, 10.0F, 0.0F, false);

        horns = new AdvancedModelBox(this, "horns");
        horns.setRotationPoint(0.0F, 0, 0.0F);
        head.addChild(horns);
        horns.setTextureOffset(0, 0).addBox(-2.0F, -12.0F, -18.0F, 4.0F, 12.0F, 5.0F, 0.0F, false);
        horns.setTextureOffset(0, 44).addBox(-2.0F, -4.0F, -13.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        leftEar = new AdvancedModelBox(this, "leftEar");
        leftEar.setRotationPoint(6.0F, -5.0F, -4.0F);
        head.addChild(leftEar);
        setRotationAngle(leftEar, -0.2443F, -0.2443F, 0.7679F);
        leftEar.setTextureOffset(0, 53).addBox(-1.0F, -5.0F, 0.0F, 3.0F, 6.0F, 1.0F, 0.0F, false);

        rightEar = new AdvancedModelBox(this, "rightEar");
        rightEar.setRotationPoint(-6.0F, -5.0F, -4.0F);
        head.addChild(rightEar);
        setRotationAngle(rightEar, -0.2443F, 0.2443F, -0.7679F);
        rightEar.setTextureOffset(0, 53).addBox(-2.0F, -5.0F, 0.0F, 3.0F, 6.0F, 1.0F, 0.0F, true);

        leftArm = new AdvancedModelBox(this, "leftArm");
        leftArm.setRotationPoint(7.3F, 11.0F, -8.0F);
        chest.addChild(leftArm);
        leftArm.setTextureOffset(79, 59).addBox(-4.0F, 2.0F, -4.0F, 7.0F, 10.0F, 7.0F, 0.0F, false);

        rightArm = new AdvancedModelBox(this, "rightArm");
        rightArm.setRotationPoint(-7.3F, 11.0F, -8.0F);
        chest.addChild(rightArm);
        rightArm.setTextureOffset(79, 59).addBox(-3.0F, 2.0F, -4.0F, 7.0F, 10.0F, 7.0F, 0.0F, true);
        this.updateDefaultPose();
        animator = ModelAnimator.create();
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        animator.update(entity);
        animator.setAnimation(EntityRhinoceros.ANIMATION_FLICK_EARS);
        animator.startKeyframe(2);
        animator.rotate(head, 0, 0, Maths.rad(10));
        animator.rotate(rightEar, 0, Maths.rad(25), Maths.rad(40));
        animator.rotate(leftEar, 0, Maths.rad(-25), Maths.rad(-40));
        animator.endKeyframe();
        animator.startKeyframe(2);
        animator.rotate(head, 0, 0, Maths.rad(-10));
        animator.rotate(rightEar, 0, 0, 0);
        animator.rotate(leftEar, 0, 0, 0);
        animator.endKeyframe();
        animator.startKeyframe(2);
        animator.rotate(head, 0, 0, Maths.rad(10));
        animator.rotate(rightEar, 0, Maths.rad(5), Maths.rad(-40));
        animator.rotate(leftEar, 0, Maths.rad(-5), Maths.rad(40));
        animator.endKeyframe();
        animator.startKeyframe(2);
        animator.rotate(head, 0, 0, Maths.rad(-10));
        animator.rotate(rightEar, 0, Maths.rad(25), Maths.rad(40));
        animator.rotate(leftEar, 0, Maths.rad(-25), Maths.rad(-40));
        animator.endKeyframe();
        animator.startKeyframe(2);
        animator.rotate(head, 0, 0, Maths.rad(10));
        animator.rotate(rightEar, 0, 0, 0);
        animator.rotate(leftEar, 0, 0, 0);
        animator.endKeyframe();
        animator.startKeyframe(2);
        animator.rotate(head, 0, 0, 0);
        animator.rotate(rightEar, 0, Maths.rad(5), Maths.rad(-40));
        animator.rotate(leftEar, 0, Maths.rad(-5), Maths.rad(40));
        animator.endKeyframe();
        animator.resetKeyframe(7);
        animator.setAnimation(EntityRhinoceros.ANIMATION_EAT_GRASS);
        animator.startKeyframe(5);
        eatPose();
        animator.endKeyframe();
        animator.startKeyframe(5);
        eatPose();
        animator.move(head, 0, 1, 1);
        animator.rotate(head, Maths.rad(10), 0, 0);
        animator.endKeyframe();
        animator.startKeyframe(5);
        eatPose();
        animator.endKeyframe();
        animator.startKeyframe(5);
        eatPose();
        animator.move(head, 0, 1, 1);
        animator.rotate(head, Maths.rad(10), 0, 0);
        animator.endKeyframe();
        animator.startKeyframe(5);
        eatPose();
        animator.endKeyframe();
        animator.startKeyframe(5);
        eatPose();
        animator.move(head, 0, 1, 1);
        animator.rotate(head, Maths.rad(10), 0, 0);
        animator.endKeyframe();
        animator.resetKeyframe(5);
        animator.setAnimation(EntityRhinoceros.ANIMATION_FLING);
        animator.startKeyframe(5);
        animator.move(body, 0, 1, -2);
        animator.move(leftArm, 0, -1, 0);
        animator.move(rightArm, 0, -1, 0);
        animator.move(rightLeg, 0, 1, 0);
        animator.move(leftLeg, 0, 1, 0);
        animator.rotate(body, Maths.rad(10), 0, 0);
        animator.rotate(leftLeg, Maths.rad(-10), 0, 0);
        animator.rotate(rightLeg, Maths.rad(-10), 0, 0);
        animator.rotate(head, Maths.rad(20), 0, 0);
        animator.rotate(rightArm, Maths.rad(-50), 0,  Maths.rad(5));
        animator.rotate(leftArm, Maths.rad(-50), 0,  Maths.rad(-5));
        animator.endKeyframe();
        animator.startKeyframe(3);
        animator.rotate(head, Maths.rad(-60), 0, 0);
        animator.endKeyframe();
        animator.setStaticKeyframe(2);
        animator.resetKeyframe(5);
        animator.setAnimation(EntityRhinoceros.ANIMATION_SLASH);
        animator.startKeyframe(5);
        animator.move(body, 0, 0, 5);
        animator.rotate(rightLeg, Maths.rad(-30), 0, 0);
        animator.rotate(leftLeg, Maths.rad(-30), 0, 0);
        animator.rotate(rightArm, Maths.rad(-30), 0, 0);
        animator.rotate(leftArm, Maths.rad(-30), 0, 0);
        animator.move(head, 0, 0, -4);
        animator.rotate(head, Maths.rad(40), Maths.rad(45), Maths.rad(70));
        animator.endKeyframe();
        animator.startKeyframe(5);
        animator.move(body, 0, 0, -2);
        animator.move(head, 0, 0, -2);
        animator.rotate(head, Maths.rad(-20), Maths.rad(-45), Maths.rad(-50));
        animator.endKeyframe();
        animator.startKeyframe(5);
        animator.move(head, 0, 0, -4);
        animator.rotate(head, Maths.rad(40), Maths.rad(-45), Maths.rad(-70));
        animator.endKeyframe();
        animator.startKeyframe(5);
        animator.move(body, 0, 0, -2);
        animator.move(head, 0, 0, -2);
        animator.rotate(head, Maths.rad(-20), Maths.rad(45), Maths.rad(50));
        animator.endKeyframe();
        animator.setStaticKeyframe(5);
        animator.resetKeyframe(5);
    }

    private void eatPose(){
        animator.rotate(body, Maths.rad(10), 0, 0);
        animator.rotate(rightLeg, Maths.rad(-10), 0, 0);
        animator.rotate(leftLeg, Maths.rad(-10), 0, 0);
        animator.rotate(rightArm, Maths.rad(-10), 0, 0);
        animator.rotate(leftArm, Maths.rad(-10), 0, 0);
        animator.rotate(head, Maths.rad(30), 0, 0);
        animator.move(head, 0, -4F, -2);
        animator.move(rightLeg, 0, 1.8F, -2);
        animator.move(leftLeg, 0, 1.8F, -2);
        animator.move(rightArm, 0, -3, 0);
        animator.move(leftArm, 0, -3, 0);
    }

    @Override
    public void setupAnim(EntityRhinoceros entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        float walkSpeed = 0.7F;
        float walkDegree = 0.6F;
        float idleSpeed = 0.1F;
        float idleDegree = 0.1F;
        this.walk(leftArm, walkSpeed, walkDegree * 1.2F, true, 0F, 0F, limbSwing, limbSwingAmount);
        this.walk(rightArm, walkSpeed, walkDegree * 1.2F, false, 0F, 0F, limbSwing, limbSwingAmount);
        this.walk(leftLeg, walkSpeed, walkDegree * 1.2F, false, 0F, 0F, limbSwing, limbSwingAmount);
        this.walk(rightLeg, walkSpeed, walkDegree * 1.2F, true, 0F, 0F, limbSwing, limbSwingAmount);
        this.bob(body, walkSpeed, walkDegree * 2F, true, limbSwing, limbSwingAmount);
        this.bob(head, walkSpeed, walkDegree * 2F, true, limbSwing, limbSwingAmount);
        this.walk(head, idleSpeed, idleDegree * 0.5F, false, 0F, 0.05F, ageInTicks, 1);
        this.flap(leftEar, idleSpeed, idleDegree, false, -1F, 0F, ageInTicks, 1);
        this.walk(rightEar, idleSpeed, idleDegree, false, -1F, 0F, ageInTicks, 1);
        this.head.rotateAngleY += netHeadYaw * 0.8F * Mth.DEG_TO_RAD;
        this.head.rotateAngleX += headPitch * Mth.DEG_TO_RAD;
    }

    public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
        AdvancedModelBox.rotateAngleX = x;
        AdvancedModelBox.rotateAngleY = y;
        AdvancedModelBox.rotateAngleZ = z;
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, head, chest, leftArm, leftEar, leftLeg, rightArm, rightEar, rightLeg, horns);
    }

    @Override
    public Iterable<BasicModelPart> parts() {
        return ImmutableList.of(root);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.young) {
            float f = 1.35F;
            float feet = 1.3F;
            head.setScale(f, f, f);
            head.setShouldScaleChildren(true);
            horns.showModel = false;
            leftArm.setScale(1, feet, 1);
            rightArm.setScale(1, feet, 1);
            leftLeg.setScale(1, feet, 1);
            rightLeg.setScale(1, feet, 1);
            matrixStackIn.pushPose();
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
            matrixStackIn.translate(0.0D, 1.3D, 0D);
            parts().forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
            head.setScale(1, 1, 1);
            leftArm.setScale(1, 1, 1);
            rightArm.setScale(1, 1, 1);
            leftLeg.setScale(1, 1, 1);
            rightLeg.setScale(1, 1, 1);
            horns.showModel = true;
        } else {
            matrixStackIn.pushPose();
            parts().forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.popPose();
        }
    }

}

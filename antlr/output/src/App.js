import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, onMultipleAnswerChange} from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, Image, ResponsiveContext, TextInput, Meter } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
	}

	render() {
		var customBreakpoints = deepMerge(grommet, {
			global: {
				breakpoints: {
					small: {
						value: 600
					},
					medium: {
						value: 950
					},
					large: 3000
				},
			}
		});
		var c_areas= []
		const areas = {
			default: [
				["header","header",],
				["left","middle",],
			],
			small: [
				["header",],
				["middle",],
			],
		}
		const rows = {
			default:['small','medium',],
			small:['small','medium','large',],
		}
		const columns = {
			default:['small','large',],
			small:[],
		}
		return (
			<Grommet theme={customBreakpoints}>
				<ResponsiveContext.Consumer>
					{size =>
						<Grid
							rows={rows[size] ? rows[size] : rows["default"]}
							columns={columns[size] ? columns[size] : columns["default"]}
							gap='null'
							areas={areas[size] ? areas[size] : areas["default"]}>
							{
								c_areas =  areas[size] ? areas[size] : areas["default"]}{
								c_areas.find((row) => row.indexOf("header") >=0) ?
								<Box gridArea='header' align='center'   background='light-5' round='small' border={{size: "small",}}>
								<Text size='large'  textAlign='center'  >{this.state.quiz.title}</Text>
								<Text size='medium'  textAlign='center'  >{this.state.quiz.theme}</Text>
								</Box>
								:
								<Box/>
							}
							{
								c_areas =  areas[size] ? areas[size] : areas["default"]}{
								c_areas.find((row) => row.indexOf("middle") >=0) ?
								<Box gridArea='middle' align='center'   background='light-3' >
							{this.state.quiz.questions.map((question,i)=>{return(<>						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[i].statement}</Text>
								<CheckBoxGroup options = { this.state.quiz.questions[i].answers } onChange={ ({ value, option }) => { this.setState ({ quiz : onMultipleAnswerChange(this.state.quiz,value,option)}) } } gap = 'small'  />
								</>)})}						</Box>
								:
								<Box/>
							}
							{
								c_areas =  areas[size] ? areas[size] : areas["default"]}{
								c_areas.find((row) => row.indexOf("left") >=0) ?
								<Box gridArea='left' align='center'   background='brand' >
								<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
								</Box>
								:
								<Box/>
							}
						</Grid>
					}
				</ResponsiveContext.Consumer>
			</Grommet>
		);
	}
}